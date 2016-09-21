CREATE  TABLE IF NOT EXISTS  `function` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `function_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci

CREATE  TABLE IF NOT EXISTS  `role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `role_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci

CREATE  TABLE IF NOT EXISTS  `user` (
  `id` VARCHAR(45) NOT NULL ,
  `user_name` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL DEFAULT '000000' ,
  `chinese_name` VARCHAR(45) NULL ,
  `gender` VARCHAR(45) NOT NULL ,
  `role_type` VARCHAR(45) NOT NULL ,
  `tel_number` VARCHAR(45) NOT NULL ,
  `email` VARCHAR(45) NOT NULL ,
  `photo_address` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci

CREATE  TABLE IF NOT EXISTS  `user_role` (
  `user_id` VARCHAR(45) NOT NULL ,
  `role_id` INT(11) NOT NULL ,
  INDEX `user_role_fk_user_id` (`user_id` ASC) ,
  INDEX `user_role_fk_role_id` (`role_id` ASC) ,
  CONSTRAINT `user_role_fk_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES  `user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_role_fk_role_id`
    FOREIGN KEY (`role_id` )
    REFERENCES  `role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci

CREATE  TABLE IF NOT EXISTS  `user_role` (
  `user_id` VARCHAR(45) NOT NULL ,
  `role_id` INT(11) NOT NULL ,
  INDEX `user_role_fk_user_id` (`user_id` ASC) ,
  INDEX `user_role_fk_role_id` (`role_id` ASC) ,
  CONSTRAINT `user_role_fk_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES  `user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_role_fk_role_id`
    FOREIGN KEY (`role_id` )
    REFERENCES  `role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci

CREATE  TABLE IF NOT EXISTS  `role_function` (
  `role_id` INT(11) NOT NULL ,
  `function_id` INT(11) NOT NULL ,
  INDEX `role_function_fk_role_id` (`role_id` ASC) ,
  INDEX `role_function_fk_function_id` (`function_id` ASC) ,
  CONSTRAINT `role_function_fk_role_id`
    FOREIGN KEY (`role_id` )
    REFERENCES  `role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `role_function_fk_function_id`
    FOREIGN KEY (`function_id` )
    REFERENCES  `function` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci

CREATE  TABLE IF NOT EXISTS  `exam` (
  `id` VARCHAR(45) NOT NULL ,
  `exam_name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(45) NULL ,
  `effective_time` DATETIME NOT NULL ,
  `duration` VARCHAR(20) NOT NULL ,
  `question_quantity` INT(11) NOT NULL ,
  `question_points` INT(11) NOT NULL ,
  `pass_criteria` INT(11) NOT NULL ,
  `full_score` DOUBLE NOT NULL ,
  `creator` VARCHAR(45) NULL ,
  `type` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci

CREATE  TABLE IF NOT EXISTS  `user_exam` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `user_id` VARCHAR(45) NOT NULL ,
  `exam_id` VARCHAR(45) NOT NULL ,
  `score` DOUBLE NULL ,
  `status` VARCHAR(45) NOT NULL DEFAULT 0 ,
  INDEX `fk_exam_id` (`exam_id` ASC) ,
  INDEX `fk_user_id` (`user_id` ASC) ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_exam_id`
    FOREIGN KEY (`exam_id` )
    REFERENCES  `exam` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES  `user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM
DEFAULT CHARACTER SET = greek
COLLATE = greek_general_ci

CREATE  TABLE IF NOT EXISTS  `paper` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `question_id` INT(11) NULL ,
  `exam_id` VARCHAR(45) NULL ,
  `user_id` VARCHAR(45) NULL ,
  `answer` VARCHAR(45) NULL ,
  INDEX `fk_exam_id` (`exam_id` ASC) ,
  INDEX `paper_user_fk_user_id` (`user_id` ASC) ,
  INDEX `fk_question_id` (`question_id` ASC) ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_exam_id`
    FOREIGN KEY (`exam_id` )
    REFERENCES  `exam` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `paper_user_fk_user_id`
    FOREIGN KEY (`user_id` )
    REFERENCES  `user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_question_id`
    FOREIGN KEY (`question_id` )
    REFERENCES  `question` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci

CREATE  TABLE IF NOT EXISTS `question` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `display_id` VARCHAR(45) NOT NULL ,
  `question_content` VARCHAR(45) NOT NULL ,
  `option_one` VARCHAR(45) NOT NULL ,
  `option_two` VARCHAR(45) NOT NULL ,
  `option_three` VARCHAR(45) NOT NULL ,
  `option_four` VARCHAR(45) NOT NULL ,
  `correct_option` VARCHAR(45) NOT NULL ,
  `isdelete` TINYINT(1) NULL DEFAULT false ,
  `previous_id` INT(11) NULL DEFAULT 0 ,
  PRIMARY KEY (`id`) )
ENGINE = MyISAM
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci